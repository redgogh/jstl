use std::time::Duration;
use tokio;
mod chromedriver;

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let chromeclient = chromedriver::open_web_client("https://www.rust-lang.org").await?;

    loop {
        if chromeclient.is_bottom().await {
            break;
        }

        tokio::time::sleep(Duration::from_secs(1)).await;
        chromeclient.scroll(1000).await;
    }

    chromeclient.close().await;

    Ok(())
}