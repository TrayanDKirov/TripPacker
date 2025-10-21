import { PackingGroupResponseDto } from "../packing-group/packing-group-response.dto";
import { PackingItemResponseDto } from "../packing-item/packing-item-response.dto";

export interface TripLuggageResponseDto {
  name: string;

  packingGroups: PackingGroupResponseDto[];
  packingItems: PackingItemResponseDto[];

  createdAt: string;
}