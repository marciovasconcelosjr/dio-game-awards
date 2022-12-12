const baseApiURL = "http://192.168.0.35:8080/api/"


export async function clientGetGames(){
    const response = await fetch(`${baseApiURL}games`)
    const json = await response.json()
    console.log(json)
    return json
}

export async function clientSendingVotes(id:number){

    const requestOption ={
        method: 'PATCH'
    }

    fetch(`${baseApiURL}games/${id}/vote`, requestOption)
    .then( response => response.text())
    .then ( result => console.log(result))
    .catch( error => console.log('error', error))
}

export async function clientGetWinner(){
    const response = await fetch(`${baseApiURL}games`)
    const json = await response.json()
    return json[0]
}