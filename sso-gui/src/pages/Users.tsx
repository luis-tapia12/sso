import Form from '../smart-components/Form';
import SmartTable, { FormProps } from '../smart-components/SmartTable';
import { User, useUsers } from '../store/useUsers';

const Users = () => {
	const {
		users,
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
	} = useUsers();

	const schema = {
		username: 'Username',
		firstName: 'First name',
		lastName: 'Last name',
		city: 'City',
		dateOfBirth: 'Date of birth',
		gender: 'Gender',
		phone: 'Phone'
	};

	if (loading && !users.length) return 'Loading';

	const UserForm = ({ form, modalMode, handleCloseModal }: FormProps<User>) => (
		<div>
			<Form
				id="user-form"
				form={form}
				schema={schema}
				hanldeSubmit={modalMode === 'create' ? handleCreate : handleUpdate}
			/>
			<div>
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
				data={users}
				handleConfirm={handleDelete}
				formComponent={UserForm}
				page={page}
				schema={schema}
				setPage={setPage}
				totalPages={totalPages}
			/>
		</div>
	);
};

export default Users;
