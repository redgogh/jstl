use std::time::Duration;
use tokio;
mod web_driver;

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    // open web client
    let web_client = web_driver::open_web_client("https://tht.changhong.com/#/user/login").await?;

    web_client.set_value("//input[@id='account']", "admin").await?;
    web_client.set_value("//input[@id='password']", "sei654321").await?;
    web_client.click("//button[span[text() = '登 录']]").await?;

    loop {
        if web_client.is_bottom().await {
            break;
        }

        tokio::time::sleep(Duration::from_secs(1)).await;
        web_client.scroll(1000).await;
    }

    web_client.close().await;

    Ok(())
}