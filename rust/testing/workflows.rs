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
use serde::{Deserialize};

#[derive(Debug, Deserialize)]
pub struct Workflows {
    pub app: String,
}

#[derive(Debug, Deserialize)]
pub struct Task {
    pub name: String,
    pub commands: Vec<String>,
}

#[derive(Debug, Deserialize)]
pub struct Configuration {
    pub workflows: Workflows,
    pub jobs: Vec<Task>,
}

pub enum Instruction {
    LOC,
    SET,
    CLICK,
    LOOP,
    END,
}

pub struct Command {
    pub inst: Instruction,
    pub value: String,
}

impl Command {
    fn new(inst: Instruction, value: String) -> Self {
        Self { inst, value }
    }
}

pub fn cmd_parse(text: String) -> Result<Command, Box<dyn std::error::Error>> {
    if text.starts_with("@loc") {
        return Ok(Command::new(Instruction::LOC, text.replace("@loc ", "")));
    }

    if text.starts_with("@set") {
        return Ok(Command::new(Instruction::LOC, text.replace("@set ", "")));
    }

    if text.starts_with("@click") {
        return Ok(Command::new(Instruction::CLICK, String::new()));
    }

    Err(Box::new(std::io::Error::new(std::io::ErrorKind::InvalidInput, "")))
}

pub fn load_workflows_cnf(path: &str) -> Result<Configuration, Box<dyn std::error::Error>> {
    let yaml_content = fs::read_to_string(path)?;
    Ok(serde_yaml::from_str::<Configuration>(&yaml_content)?)
}