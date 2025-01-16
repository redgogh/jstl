use fantoccini::{Client, ClientBuilder};

const CHROME_DRIVER: &str = "http://127.0.0.1:9515";

///
/// 判断页面是否在最底部
///
///
pub async fn is_page_bottom(client: &Client) -> bool {
    let ret = client.execute(r#"
        return window.scrollY + window.innerHeight >= document.body.scrollHeight;
    "#, vec![]).await;

    ret.unwrap().as_bool().unwrap()
}

///
/// 模拟滑动当前打开的页面
///
///
pub async fn scroll_by(px: i64, client: &Client) {
    client.execute(r#"
        window.scrollBy(arguments[0], window.innerHeight);
    "#, vec![px.into()]).await.expect("Got error");
}

///
/// 打开 web 驱动程序并指定打开 web 页面
///
///
pub async fn open_web_driver(url: &str) -> Result<Client, Box<dyn std::error::Error>>{
    let client = ClientBuilder::native().connect(CHROME_DRIVER).await?;
    client.goto(url).await?;
    Ok(client)
}