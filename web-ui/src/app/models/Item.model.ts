export class Item {
  constructor(
    public id: string,
    public usrId: string,
    public name: string,
    public prize: number,
    public category: string,
    public description: string,
    public state: string,
    public image: string,
    public report: number,
    public date: number
  ){}
}
