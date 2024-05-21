import Form from '../smart-components/Form';
import SmartTable, { FormProps } from '../smart-components/SmartTable';
import { Application, useApplications } from '../store/useApplications';
import styles from '../styles/Form.module.css';

const Applications = () => {
	const {
		applications,
		page,
		totalPages,
		loading,
		sort,
		order,
		handleCreate,
		handleDelete,
		handleUpdate,
		setPage,
		setSort
	} = useApplications();

	const schema = {
		name: 'Client name',
		clientId: 'Client Id',
		redirectUrl: 'Redirect URL'
	};

	if (loading && !applications.length) return 'Loading';

	const ApplicationForm = ({ form, modalMode, handleCloseModal }: FormProps<Application>) => (
		<div>
			<Form
				id="user-form"
				form={form}
				schema={schema}
				hanldeSubmit={modalMode === 'create' ? handleCreate : handleUpdate}
			/>
			<div className={styles.actions}>
				<button type="submit" form="user-form" disabled={form.formState.isSubmitting}>
					Accept
				</button>
				<button onClick={handleCloseModal} disabled={form.formState.isSubmitting}>
					Cancel
				</button>
			</div>
		</div>
	);

	return (
		<div>
			<SmartTable
				sort={sort}
				order={order}
				setSort={setSort}
				data={applications}
				handleConfirm={handleDelete}
				formComponent={ApplicationForm}
				page={page}
				schema={schema}
				setPage={setPage}
				totalPages={totalPages}
			/>
		</div>
	);
};

export default Applications;
