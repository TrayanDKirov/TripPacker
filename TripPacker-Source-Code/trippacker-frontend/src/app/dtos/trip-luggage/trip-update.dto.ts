import { PackingGroupCreateDto } from "../packing-group/packing-group-create.dto";

export interface TripLuggageUpdateDto {
    newName: string | null;
    newGroups: PackingGroupCreateDto[] | null;
    newGroup: PackingGroupCreateDto | null;
}