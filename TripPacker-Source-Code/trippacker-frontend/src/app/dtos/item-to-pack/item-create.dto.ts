export interface ItemToPackCreateDto {
    name: string;

    quantityToPack: number;

    tripId: number | null;
    groupId: number | null;
}