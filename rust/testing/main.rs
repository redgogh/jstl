use std::time::Duration;
use tokio;
mod web_driver;

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let driver = web_driver::open_web_driver("https://www.rust-lang.org").await?;

    loop {
        if driver.is_page_bottom().await {
            break;
        }

        tokio::time::sleep(Duration::from_secs(1)).await;
        driver.scroll_by(1000).await;
    }

    driver.close().await;

    Ok(())
}