use fantoccini::{Client, ClientBuilder, Locator};

const CHROME_DRIVER: &str = "http://127.0.0.1:9515";

pub struct WebClient {
    client: Client
}



impl WebClient {
    fn new(client: Client) -> Self {
        Self { client }
    }

    pub async fn is_bottom(&self) -> bool {
        let ret = self.client.execute(r#"
            return window.scrollY + window.innerHeight >= document.body.scrollHeight;
        "#, vec![]).await;

        ret.unwrap().as_bool().unwrap()
    }

    pub async fn scroll(&self, px: i64) {
        self.client.execute(r#"
            window.scrollBy(arguments[0], window.innerHeight);
        "#, vec![px.into()]).await.expect("[WebClient] 客户端脚本执行失败");
    }

    pub async fn click(&self, xpath: &str) -> Result<(), Box<dyn std::error::Error>> {
        let btn = self.client.find(Locator::XPath(xpath)).await?;
        btn.click().await?;
        Ok(())
    }

    pub async fn set_value(&self, xpath: &str, value: &str) -> Result<(), Box<dyn std::error::Error>> {
        let input = self.client.find(Locator::XPath(xpath)).await?;
        input.send_keys(value).await?;
        Ok(())
    }

    pub async fn close(self) {
        self.client.close().await.expect("Got error");
    }

}

pub async fn open_web_client(url: &str) -> Result<WebClient, Box<dyn std::error::Error>>{
    let client = ClientBuilder::native().connect(CHROME_DRIVER).await?;
    client.goto(url).await?;
    Ok(WebClient::new(client))
}