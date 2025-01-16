use tokio;
use fantoccini::{Client, ClientBuilder};

async fn open_web_driver(url: &str) -> Result<Client, Box<dyn std::error::Error>>{
    let client = ClientBuilder::native().connect("http://127.0.0.1:9515").await?;
    client.goto(url).await?;
    Ok(client)
}

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let client = open_web_driver("https://www.rust-lang.org").await?;

    client.execute(
        r#"window.scrollBy({ top: 1000, behavior: 'smooth' });"#,
        vec![],
    ).await?;

    Ok(())
}