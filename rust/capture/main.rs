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

use tokio;
mod web_driver;

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    // open web client
    let client = web_driver::open_web_client("https://tht.changhong.com/#/user/login").await?;

    if let Some(account) = client.find("//input[@id='account']").await {
        client.send_text(&account, "admin").await?;
    }

    if let Some(account) = client.find("//input[@id='password']").await {
        client.send_text(&account, "sei654321").await?;
    }

    if let Some(btn) = client.find("//button[span[text() = '登 录']]").await {
        client.click(&btn).await?;
    }

    if let Some(btn) = client.find("//button[@aria-label='Close' and contains(@class, 'ant-modal-close')]").await {
        client.click(&btn).await?;
    }

    loop {
        tokio::time::sleep(std::time::Duration::from_millis(500)).await;
        match client.find("//button[@aria-label='Close' and contains(@class, 'ant-modal-close')]").await {
            Some(btn) => {
                client.click(&btn).await?;
                break;
            }
            None => {}
        }
    }

    tokio::time::sleep(std::time::Duration::from_secs(5)).await;

    client.close().await;

    Ok(())
}