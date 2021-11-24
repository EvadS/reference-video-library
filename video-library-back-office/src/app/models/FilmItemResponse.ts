export class FilmItemResponse {
  constructor(
    public  id?: number,
    public name?: string,
    public director?: string,
    public duration?: number,
    public  isPublished?: boolean
  ) {
  }
}
