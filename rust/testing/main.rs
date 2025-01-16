use fantoccini::{Client, Locator};
use tokio;
use std::vec::Vec;

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let mut client = Client::new("http://localhost:8080").await?;
    client.goto("https://tht.changhong.com/#/DashBoard").await?;

    let _ = client.execute(
        r#"window.scrollTo(0, document.body.scrollHeight);"#, vec![]
    ).await?;

    tokio::time::sleep(std::time::Duration::from_secs(3)).await;

    client.close().await?;

    Ok(())
}