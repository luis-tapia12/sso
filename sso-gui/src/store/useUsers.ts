import { useEffect } from 'react';
import { create } from 'zustand';

import client from '../config/client';
import { Page } from '../types';
import { objectArray } from '../helpers/objectArray';

export type User = {
	id: string;
	username: string;
	firstName: string;
	lastName: string;
	city: string;
	dateOfBirth: string;
	gender: string;
	phone: string;
	profilePicture: string;
};

type UsersState = {
	users: User[];
	page: number;
	totalPages: number;
	loading: boolean;
	sort: string;
	order: 'asc' | 'desc';
	setPage: (page: number) => void;
	setUsersPage: (usersPage: Page<User>) => void;
	setLoading: (loading: boolean) => void;
	setUsers: (users: User[]) => void;
	setSort: (column: string) => void;
};

export const useUsersStore = create<UsersState>((set) => ({
	users: [],
	page: 0,
	totalPages: 10,
	loading: true,
	sort: '',
	order: 'desc',
	setPage: (page) => set(() => ({ page })),
	setUsersPage: (usersPage) =>
		set(() => ({
			users: usersPage.content,
			page: usersPage.number,
			totalPages: usersPage.totalPages
		})),
	setLoading: (loading) => set(() => ({ loading })),
	setUsers: (users) => set(() => ({ users })),
	setSort: (column) =>
		set((state) => ({ sort: column, order: state.order === 'asc' ? 'desc' : 'asc' }))
}));

export const useUsers = () => {
	const store = useUsersStore();

	const fetchData = () => {
		store.setLoading(true);
		client
			.get(`/users?page=${store.page}&sort=${store.sort}&order=${store.order}`)
			.then(({ data }) => {
				store.setUsersPage(data);
			})
			.finally(() => {
				store.setLoading(false);
			});
	};

	useEffect(() => {
		fetchData();
	}, [store.page, store.sort, store.order]); // eslint-disable-line

	const handleCreate = async (user: User) => {
		try {
			await client.post('/users', { ...user, password: 'password' });
			fetchData();
		} catch (error) {
			console.log(error);
		}
	};

	const handleDelete = async (user: User) => {
		return client.delete(`/users/${user.id}`).then(() => {
			fetchData();
			return Promise.resolve();
		});
	};

	const handleUpdate = async (user: User) => {
		try {
			const { data } = await client.patch(`/users/${user.id}`, user);
			store.setUsers(objectArray(store.users).update(data));
		} catch (error) {
			console.log(error);
		}
	};

	return {
		...store,
		handleCreate,
		handleDelete,
		handleUpdate
	};
};
