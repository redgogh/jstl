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
use fantoccini::elements::Element;
use fantoccini::key::Key::Command;
use tokio;
mod web_driver;
mod workflows;

/// 异步执行按秒单位休眠
///
macro_rules! sleep_secs_await {
    ($expr:expr) => {
        tokio::time::sleep(Duration::from_secs($expr)).await;
    };
}

/// 自动化测试框架入口函数
///
#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    // open web client
    let web_client = web_driver::open_web_client("https://tht.changhong.com/#/user/login").await?;

    let cnf = workflows::load_workflows_cnf("./workflows.yaml")?;

    let mut commands: Vec<workflows::Command> = Vec::new();

    for task in cnf.jobs {
        for cmd in task.commands {
            let cmd = workflows::cmd_parse(cmd).expect("未知指令");
            commands.push(cmd);
        }
    }

    let mut tab_from_locator: Element;

    for cmd in commands {
        match cmd.inst {
            LOC => tab_from_locator = web_client.locator(&cmd.value).await?,
            SET => web_client.locator_set_value(&tab_from_locator, &cmd.value).await?,
            CLICK => web_client.click_locator(&tab_from_locator).await?,
        }
    }

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