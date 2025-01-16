use tokio;
use fantoccini::{Client, ClientBuilder};

const CHROME_DRIVER: &str = "http://127.0.0.1:9515";

async fn open_web_driver(url: &str) -> Result<Client, Box<dyn std::error::Error>>{
    let client = ClientBuilder::native().connect(CHROME_DRIVER).await?;
    client.goto(url).await?;
    Ok(client)
}

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let client = open_web_driver("https://www.rust-lang.org").await?;

    client.execute(
        r#"
        window.scrollBy(0, window.innerHeight);
        "#,
        vec![],
    ).await?;

    Ok(())
}