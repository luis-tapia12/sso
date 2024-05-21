import { createElement } from 'react';
import { FieldValues, UseFormReturn } from 'react-hook-form';

import ConfirmationModal from './ConfirmationModal';
import Modal from '../components/Modal';
import Table, { Schema } from '../components/Table';
import { ModalMode, useSmartTable } from '../hooks/useSmartTable';
import styles from '../styles/SmartTable.module.css';

type SmartTableProps<T extends FieldValues> = {
	data: T[];
	order?: 'asc' | 'desc';
	sort?: string;
	formComponent: React.FC<FormProps<T>>;
	page: number;
	schema: Schema<string, T>;
	totalPages: number;
	handleConfirm: (item: T) => Promise<unknown>;
	setPage: (page: number) => void;
	setSort?: (column: string) => void;
};

export type FormProps<T extends FieldValues> = {
	form: UseFormReturn<T, unknown, undefined>;
	modalMode: ModalMode;
	handleCloseModal: () => void;
};

const SmartTable = <T extends Record<string, unknown>>({
	page,
	formComponent,
	schema,
	totalPages,
	handleConfirm,
	setPage,
	...rest
}: SmartTableProps<T>) => {
	const {
		editingPage,
		form,
		localPage,
		modalMode,
		handleClickAdd,
		handleClickDelete,
		handleClickEdit,
		handleClickNext,
		handleClickPrev,
		handleCloseModal,
		handleChangePage,
		handleEditPage
	} = useSmartTable<T>(page, totalPages, setPage);

	const smartSchema: Schema<string, T> = {
		...schema,
		' ': {
			label: 'Action',
			render: (item) => (
				<div className={styles.rowActions}>
					<button onClick={() => handleClickEdit(item)}>
						<i className="fas fa-pen" />
					</button>
					<button onClick={() => handleClickDelete(item)}>
						<i className="fas fa-trash" />
					</button>
				</div>
			)
		}
	};

	return (
		<div>
			<div className={styles.actions}>
				<button onClick={handleClickAdd}>
					New <i className="fas fa-plus" />
				</button>
			</div>
			<Table {...rest} schema={smartSchema} className={styles.table} />
			<div className={styles.pagination}>
				<button disabled={page + 1 <= 1} onClick={handleClickPrev}>
					<i className="fas fa-angle-left" />
				</button>
				<form>
					<span>
						{`${page + 1} of `}
						{editingPage ? (
							<input
								onChange={handleChangePage}
								type="number"
								value={localPage || ''}
							/>
						) : (
							totalPages
						)}
					</span>
					<button onClick={handleEditPage}>{editingPage ? 'Accept' : 'Edit'}</button>
				</form>
				<button disabled={page + 1 >= totalPages} onClick={handleClickNext}>
					<i className="fas fa-angle-right" />
				</button>
			</div>
			<ConfirmationModal
				show={modalMode === 'delete'}
				onAccept={() => handleConfirm(form.getValues())}
				onClose={handleCloseModal}
			/>
			<Modal
				show={modalMode === 'update' || modalMode === 'create'}
				title={modalMode as string}
				onClose={handleCloseModal}
			>
				{createElement(formComponent, { form, modalMode, handleCloseModal })}
			</Modal>
		</div>
	);
};

export default SmartTable;
