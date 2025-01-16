use fantoccini::{Client, ClientBuilder};

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

    pub async fn close(self) {
        self.client.close().await.expect("[WebClient] 客户端关闭失败");
    }

}

pub async fn open_web_client(url: &str) -> Result<WebClient, Box<dyn std::error::Error>>{
    let client = ClientBuilder::native().connect(CHROME_DRIVER).await?;
    client.goto(url).await?;
    Ok(WebClient::new(client))
}