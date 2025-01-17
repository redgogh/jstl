//!
//!
//!    Copyright (C) 2019-2024 RedGogh All rights reserved.
//!
//!    Licensed under the Apache License, Version 2.0 (the "License");
//!    you may not use this file except in compliance with the License.
//!    You may obtain a copy of the License at
//!
//!        http://www.apache.org/licenses/LICENSE-2.0
//!
//!    Unless required by applicable law or agreed to in writing, software
//!    distributed under the License is distributed on an "AS IS" BASIS,
//!    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//!    See the License for the specific language governing permissions and
//!    limitations under the License.
//!
use std::fs;
use Vec;
use serde::{Deserialize, Deserializer};

macro_rules! instruction {
    ($inst:expr, $prefix:expr, $text:expr) => {
        if $text.start_withs($prefix) {
            return Some(
                Instruction::new($inst, $text.replace($prefix + " ", "", String::from($text)))
            );
        }
    };
}

fn from_enable_bool<'de, D>(deserializer: D) -> Result<bool, D::Error>
    where D: Deserializer<'de>, {
    let value: String = Deserialize::deserialize(deserializer)?;
    match value.to_lowercase().as_str() {
        "on" => Ok(true),
        "off" => Ok(false),
        _ => Err(serde::de::Error::custom(format!("invalid value for {}", value))),
    }
}

#[derive(Debug, Deserialize)]
struct Job {
    pub name: String,
    #[serde(deserialize_with = "from_enable_bool")]
    pub enable: bool,
    pub insts: Vec<String>,
}

#[derive(Debug, Deserialize)]
pub struct Workflows {
    pub name: String,
    pub jobs: Vec<Job>,
}

pub enum InstType {
    LOC,
    SEND,
    CLICK,
    LOOP,
    END,
}

pub struct Instruction {
    pub inst: InstType,
    pub value: String,
    pub text: String,
}

impl Instruction {
    fn new(inst: InstType, value: String, text: String) -> Self {
        Self { inst, value, text }
    }
}

fn parse_inst(mut text: String) -> Option<Instruction> {
    let text = text.trim();

    instruction!(InstType::LOC, "@loc", text);
    instruction!(InstType::SEND, "@send", text);
    instruction!(InstType::CLICK, "@click", text);
    instruction!(InstType::LOOP, "@loop", text);
    instruction!(InstType::END, "@end", text);

    None
}

pub fn load(path: &String) -> Result<Workflows, Box<dyn std::error::Error>> {
    let yaml_content = fs::read_to_string(path)?;
    let workflows: Workflows = serde_yaml::from_str(&yaml_content).map_err(|e| Box::new(e))?;

    let mut instructions: Vec<Instruction> = Vec::new();

    for job in workflows.jobs {
        for inst in job.insts {
            match parse_inst(inst) {
                Some(instruction) => instructions.push(instruction),
                Err(e) => return Err(Box::new(e)),
            }
        }
    }

    Ok(workflows)
}