const app = document.getElementById('root')
const container = document.createElement('div')
container.setAttribute('class', 'container')
app.appendChild(container)

//ajax call to servlet, to get the api inforamtion json data

var request = new XMLHttpRequest()
request.open('GET', 'http://localhost:8080/Usamacoursework/mainPageServlet', true)
request.onload = function () {
    // Begin accessing JSON data here
    var inJson = JSON.parse(this.response)
    //console.log(data.data[0])
    var animeArray = inJson.data.slice(0,9)
    
    if (request.status >= 200 && request.status < 400) {
        animeArray.forEach((anime) => {
          //console.log(anime.attributes)
          const card = document.createElement('div')
          card.setAttribute('class', 'card')
    
          const h1 = document.createElement('h1')
          if( 'en' in anime.attributes.titles){
            h1.textContent = anime.attributes.titles.en
          }else{
            h1.textContent = anime.attributes.titles.en_us
          }
          //img tag for anime image
          const animeImg =  document.createElement('img')
          animeImg.src = anime.attributes.coverImage.tiny

          //p tag for anime description
          const p = document.createElement('p')
          anime.description = anime.attributes.description.substring(0, 150)
          p.textContent = `${anime.description}...`
          
          const trailer = document.createElement('a')
          trailer.setAttribute('href', 'https://www.youtube.com/watch?v='+anime.attributes.youtubeVideoId)
          trailer.setAttribute('target','_blank')
          trailer.textContent ='WatchTrailer'
          trailer.setAttribute('class','text-center btn btn-primary btn-sm')     
          container.appendChild(card)
          card.append(animeImg)
          card.appendChild(h1)
          card.appendChild(p)
          card.appendChild(trailer)

      })

  }
}
  
  request.send()