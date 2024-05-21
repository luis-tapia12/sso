import { ReactNode } from 'react';

export type Column<T> = {
	label: ReactNode;
	render?: (item: T) => ReactNode;
};

export type Schema<K extends string, T> = {
	[key in K]: ReactNode | Column<T>;
};

type TableProps<T> = {
	data: T[];
	schema: Schema<string, T>;
	order?: 'asc' | 'desc';
	sort?: string;
	setSort?: (column: string) => void;
};

const Table = <T extends Record<string, unknown>>({
	data,
	schema,
	sort,
	order,
	setSort
}: TableProps<T>) => {
	const columns = Object.keys(schema);

	const tableHead = (
		<tr>
			{columns.map((currColumn: string, columnIndex: number) => (
				<th key={columnIndex} onClick={() => setSort?.(currColumn)}>
					{(schema[currColumn] as Column<T>).label || (schema[currColumn] as ReactNode)}
					{sort === currColumn && (order === 'asc' ? '▲' : '▼')}
				</th>
			))}
		</tr>
	);

	const tableRow = (currItem: T, itemIndex: number) => (
		<tr key={itemIndex}>
			{columns.map((currColumn: string, columnIndex: number) => (
				<td key={columnIndex}>
					{((schema[currColumn] as Column<T>)?.render?.(currItem) ??
						(currItem[currColumn] as ReactNode)) ||
						'-'}
				</td>
			))}
		</tr>
	);

	return (
		<table>
			<thead>{tableHead}</thead>
			<tbody>{data.map(tableRow)}</tbody>
		</table>
	);
};

export default Table;
