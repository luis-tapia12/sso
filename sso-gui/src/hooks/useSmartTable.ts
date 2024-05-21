import { ChangeEvent, MouseEvent, useEffect, useState } from 'react';
import { FieldValues, useForm } from 'react-hook-form';

export type ModalMode = 'delete' | 'update' | 'create' | null;

export const useSmartTable = <T extends FieldValues>(
	page: number,
	totalPages: number,
	setPage: (page: number) => void
) => {
	const form = useForm<T>();
	const [editingPage, setEditingPage] = useState(false);
	const [localPage, setLocalPage] = useState(page + 1);
	const [modalMode, setModalMode] = useState<ModalMode>(null);

	useEffect(() => {
		if (!editingPage) {
			setPage(localPage - 1);
		}
	}, [localPage]); // eslint-disable-line

	useEffect(() => {
		if (form.formState.isSubmitted) {
			handleCloseModal();
		}
	}, [form.formState.isSubmitted]); // eslint-disable-line

	const handleClickNext = () => setLocalPage((prevPage) => prevPage + 1);
	const handleClickPrev = () => setLocalPage((prevPage) => prevPage - 1);

	const handleChangePage = (event: ChangeEvent<HTMLInputElement>) =>
		setLocalPage(event.target.valueAsNumber);

	const handleEditPage = (event: MouseEvent) => {
		event.preventDefault();
		if (editingPage && localPage <= totalPages && localPage > 0) {
			setPage(localPage - 1);
		}
		setEditingPage((prevEditing) => !prevEditing);
	};

	const handleCloseModal = () => (form.reset(), setModalMode(null));

	const handleClickAdd = () => setModalMode('create');
	const handleClickDelete = (item: T) => {
		form.reset(item, { keepDefaultValues: true });
		setModalMode('delete');
	};
	const handleClickEdit = (item: T) => {
		form.reset(item, { keepDefaultValues: true });
		setModalMode('update');
	};

	return {
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
	};
};
