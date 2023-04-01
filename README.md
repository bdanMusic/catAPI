# catAPI (Random Image API)

An API for getting random image URL's of my cat using Spring Boot.

## Usage

For random image URLs, use: ```http://catapi.bdan.io```

For specific image URLs, use:```http://catapi.bdan.io/img?id=1```
(for legacy mode, use: ```http://catapi.bdan.io/img?id=1&legacy=true```)

To get a list of all image URLs, use: ```http://catapi.bdan.io/urlList```
To get a list of all image names, use: ```http://catapi.bdan.io/nameList```
To get the current number of images, use: ```http://catapi.bdan.io/idCount```

## Sample Response

```
{
	"id": 119,
	"width": 2000,
	"height": 1500,
	"averageColor": "5d4d3f",
	"name": "ATTAC ON TOY",
	"url": "https://data.bdan.io/catpics/119.jpg"
}
```

## See also
 - [cat.bdan.io](https://cat.bdan.io/)
 - [catBot (Discord Bot)](https://discord.com/api/oauth2/authorize?client_id=996835929396359248&permissions=52224&scope=applications.commands%20bot)
 - [felixCaptcha](https://felixcaptcha.2n.cx/)

## Special Thanks To:
- [MJW](https://linktr.ee/coilltes)
- [daaniel](https://twitter.com/EinDaniel_)
- [frost](https://twitter.com/daedalus_ow)
- [fml42](https://github.com/fml42/)
- [Zurqee](https://twitter.com/Zurqee)
- [Kirai](https://twitter.com/kirai987)

for helping me to give names to all pictures (because i'm uncreative lol).