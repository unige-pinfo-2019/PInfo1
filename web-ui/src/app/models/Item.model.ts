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
    public date: number
  ){}
}
