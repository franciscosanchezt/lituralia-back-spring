import {Pageable} from "./pageable";

export interface Page<T> extends Pageable {
  content: T[]

}
