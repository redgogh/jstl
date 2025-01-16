use std::time::Duration;
use tokio;
mod web_driver;

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let client = web_driver::open_web_driver("https://www.rust-lang.org").await?;

    loop {
        if web_driver::is_page_bottom(&client).await {
            break;
        }

        tokio::time::sleep(Duration::from_secs(1)).await;
        web_driver::scroll_by(1000, &client).await;
    }

    Ok(())
}