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
mod web_driver;

/// 自动化测试框架入口函数
///
#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    // open web client
    let web_client = web_driver::open_web_client("https://tht.changhong.com/#/user/login").await?;

    web_client.set_value("//input[@id='account']", "admin").await?;
    web_client.set_value("//input[@id='password']", "sei654321").await?;
    web_client.click("//button[span[text() = '登 录']]").await?;

    tokio::time::sleep(Duration::from_secs(10)).await;

    loop {
        if web_client.is_bottom().await {
            break;
        }

        tokio::time::sleep(Duration::from_secs(1)).await;
        web_client.scroll(1000).await;
    }

    web_client.close().await;

    Ok(())
}