export class Item {
  constructor(
    public id: string,
    public usrId: string,
    public name: string,
    public price: number,
    public category: string,
    public description: string,
    public state: string,
    public images: string,
    public report: number,
    public date: number,
    public sold: boolean
  ){}
}

export class Annonce {
  constructor(
    public id: string,
    public usrId: string,
    public name: string,
    public category: string,
    public desc: string,
    public state: string,
    public sold: boolean
  ){}
}

export class User {
  constructor(
    public id: string,
    public name: string,
    public surname: string,
    public email: string,
    public image: string,
    public report: number,
    public userReport: string
  ){}
}




export class Data {
  constructor(
    public id: string,
    public title: string,
    public description: string,
    public datetime: string,
    public type: string,
    public animated: boolean,
    public width: number,
    public height: number,
    public size: number,
    public views: number,
    public bandwidth: number,
    public vote: string,
    public favorite: boolean,
    public nsfw: string,
    public section: string,
    public account_url: string,
    public account_id: number,
    public is_ad: boolean,
    public in_most_viral: boolean,
    public tags: any[],
    public ad_type: number,
    public ad_url: string,
    public in_gallery: boolean,
    public deletehash: string,
    public name: string,
    public link: string
  ){}
}

export class Image {
  constructor(
    public data: Data,
    public status: number,
    public succes: boolean
  ){}
}
