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
use std::time::Duration;
use tokio;
use std::fs;
mod web_driver;
use serde::{Deserialize};

/// 异步执行按秒单位休眠
///
macro_rules! sleep_secs_await {
    ($expr:expr) => {
        tokio::time::sleep(Duration::from_secs($expr)).await;
    };
}

#[derive(Debug, Deserialize)]
struct Workflows {
    app: String,
}

#[derive(Debug, Deserialize)]
struct WorkflowsConfig {
    workflows: Workflows,
}

/// 自动化测试框架入口函数
///
#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    // read workflows config
    let yaml_file_content = fs::read_to_string("./workflows.yaml")?;
    let workflows: WorkflowsConfig = serde_yaml::from_str(&yaml_file_content)?;
    println!("{:#?}", workflows);

    // open web client
    let web_client = web_driver::open_web_client("https://tht.changhong.com/#/user/login").await?;

    web_client.set_value("//input[@id='account']", "admin").await?;
    web_client.set_value("//input[@id='password']", "sei654321").await?;
    web_client.click("//button[span[text() = '登 录']]").await?;

    sleep_secs_await!(10);

    loop {
        if web_client.is_bottom().await {
            break;
        }

        sleep_secs_await!(10);
        web_client.scroll(1000).await;
    }

    web_client.close().await;

    Ok(())
}