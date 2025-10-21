import { PackingItemResponseDto } from "../packing-item/packing-item-response.dto";

export interface PackingGroupResponseDto {
    id: number;
    name: string;
    items: PackingItemResponseDto[];
}