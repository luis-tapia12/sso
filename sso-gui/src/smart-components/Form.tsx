import { ReactNode } from 'react';
import { FieldValues, Path, SubmitHandler, UseFormReturn } from 'react-hook-form';

import { Column, Schema } from '../components/Table';

type FormProps<T extends FieldValues> = {
	form: UseFormReturn<T, unknown, undefined>;
	id: string;
	schema: Schema<string, T>;
	hanldeSubmit: SubmitHandler<T>;
};

const Form = <T extends FieldValues>({ form, id, schema, hanldeSubmit }: FormProps<T>) => {
	const columns = Object.keys(schema);

	return (
		<form id={id} onSubmit={form.handleSubmit(hanldeSubmit)}>
			{columns.map((currColumn, columnIndex) => (
				<div key={columnIndex}>
					<label htmlFor={currColumn}>
						{(schema[currColumn] as Column<T>).label ||
							(schema[currColumn] as ReactNode)}
					</label>
					<input id={currColumn} {...form.register(currColumn as Path<T>)} />
				</div>
			))}
		</form>
	);
};

export default Form;
