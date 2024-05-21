type WithId = {
	id?: unknown;
	[key: string]: unknown;
};

export const objectArray = <T extends WithId>(array: T[]) => {
	const add = (item: T) => [...array, item];
	const remove = (id: unknown) => array.filter((currItem) => currItem.id !== id);
	const update = (item: T) =>
		array.map((currItem) => (currItem.id === item.id ? item : currItem));

	return { add, remove, update };
};
