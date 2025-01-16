use fantoccini::{Client, ClientBuilder};

const CHROME_DRIVER: &str = "http://127.0.0.1:9515";

pub struct WebDriver {
    client: Client
}

impl WebDriver {
    fn new(client: Client) -> Self {
        Self { client }
    }

    pub async fn is_page_bottom(&self) -> bool {
        let ret = self.client.execute(r#"
            return window.scrollY + window.innerHeight >= document.body.scrollHeight;
        "#, vec![]).await;

        ret.unwrap().as_bool().unwrap()
    }

    pub async fn scroll_by(&self, px: i64) {
        self.client.execute(r#"
            window.scrollBy(arguments[0], window.innerHeight);
        "#, vec![px.into()]).await.expect("Got error");
    }

    pub async fn close(self) {
        self.client.close().await.expect("Got error");
    }

}

pub async fn open_web_driver(url: &str) -> Result<WebDriver, Box<dyn std::error::Error>>{
    let client = ClientBuilder::native().connect(CHROME_DRIVER).await?;
    client.goto(url).await?;
    Ok(WebDriver::new(client))
}