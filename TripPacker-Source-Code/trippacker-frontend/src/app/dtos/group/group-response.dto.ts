import { ItemToPackResponseDto } from "../item-to-pack/item-response.dto";

export interface GroupToPackResponseDto {
    id: number;
    name: string;
    items: ItemToPackResponseDto[];
}