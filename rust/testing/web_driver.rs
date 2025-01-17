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
use fantoccini::{Client, ClientBuilder, Locator};
use fantoccini::elements::Element;

const CHROME_DRIVER: &str = "http://127.0.0.1:9515";

pub struct WebClient {
    client: Client
}

impl WebClient {
    fn new(client: Client) -> Self {
        Self { client }
    }

    /// 当前页面是否在最底部
    ///
    pub async fn is_bottom(&self) -> bool {
        let ret = self.client.execute(r#"
            return window.scrollY + window.innerHeight >= document.body.scrollHeight;
        "#, vec![]).await;

        ret.unwrap().as_bool().unwrap()
    }

    /// 控制页面往下滑动
    ///
    /// 方法参数
    ///  - `px`: 页面往下滑动多少像素
    ///
    pub async fn scroll(&self, px: i64) {
        self.client.execute(r#"
            window.scrollBy(arguments[0], window.innerHeight);
        "#, vec![px.into()]).await.expect("[WebClient] 客户端脚本执行失败");
    }

    pub async fn click_locator(&self, element: &Element) -> Result<(), Box<dyn std::error::Error>> {
        element.click().await?;
        Ok(())
    }

    /// 根据 `xpath` 描述获取某个控件并点击
    ///
    /// 方法参数：
    ///  - `xpath`: `XPath` 匹配路径
    ///
    pub async fn click(&self, xpath: &str) -> Result<(), Box<dyn std::error::Error>> {
        let btn = self.client.find(Locator::XPath(xpath)).await?;
        btn.click().await?;
        Ok(())
    }

    pub async fn locator(&self, xpath: &str) -> Result<Element, Box<dyn std::error::Error>> {
        let input = self.client.find(Locator::XPath(xpath)).await?;
        Ok(input)
    }

    /// 根据 `xpath` 描述获取某个控件并设置控件值
    ///
    /// 方法参数：
    ///  - `xpath`: `XPath` 匹配路径
    ///
    pub async fn locator_set_value(&self, element: &Element, value: &str) -> Result<(), Box<dyn std::error::Error>> {
        element.send_keys(value).await?;
        tokio::time::sleep(Duration::from_secs(1)).await;
        Ok(())
    }

    /// 根据 `xpath` 描述获取某个控件并设置控件值
    ///
    /// 方法参数：
    ///  - `xpath`: `XPath` 匹配路径
    ///
    pub async fn xpath_set_value(&self, xpath: &str, value: &str) -> Result<(), Box<dyn std::error::Error>> {
        self.set_value(self.locator(xpath).await?, value).await?;
        Ok(())
    }

    /// 关闭客户端驱动
    ///
    pub async fn close(self) {
        self.client.close().await.expect("[WebClient] 客户端关闭失败");
    }

}

/// 打开 `web` 客户端，并跳转到指定页面
///
/// 方法参数：
///  - `url`: 跳转页面地址
///
pub async fn open_web_client(url: &str) -> Result<WebClient, Box<dyn std::error::Error>>{
    let client = ClientBuilder::native().connect(CHROME_DRIVER).await?;
    client.goto(url).await?;
    Ok(WebClient::new(client))
}