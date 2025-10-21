export interface PackingItemResponseDto {
    id: number;
    isPacked: boolean;
    name: string;
    quantityToPack: number;
    packedQuantity: number;
}