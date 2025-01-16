use std::time::Duration;
use tokio;
use fantoccini::{Client, ClientBuilder};

const CHROME_DRIVER: &str = "http://127.0.0.1:9515";

async fn is_page_bottom(client: &Client) -> bool {
    let ret = client.execute(r#"
        return window.scrollY + window.innerHeight >= document.body.scrollHeight;
    "#, vec![]).await;

    ret.unwrap().as_bool().unwrap()
}

async fn open_web_driver(url: &str) -> Result<Client, Box<dyn std::error::Error>>{
    let client = ClientBuilder::native().connect(CHROME_DRIVER).await?;
    client.goto(url).await?;
    Ok(client)
}

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let client = open_web_driver("https://www.rust-lang.org").await?;

    loop {
        if is_page_bottom(&client).await {
            break;
        }

        tokio::time::sleep(Duration::from_secs(1)).await;
        client.execute(r#"
            window.scrollBy(1000, window.innerHeight);
        "#, vec![], ).await?;
    }


    Ok(())
}