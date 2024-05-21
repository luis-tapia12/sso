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
	setPage: (page: number) => void;
	setUsersPage: (usersPage: Page<User>) => void;
	setLoading: (loading: boolean) => void;
	setUsers: (users: User[]) => void;
};

export const useUsersStore = create<UsersState>((set) => ({
	users: [],
	page: 0,
	totalPages: 10,
	loading: true,
	setPage: (page: number) => set(() => ({ page })),
	setUsersPage: (usersPage: Page<User>) =>
		set(() => ({
			users: usersPage.content,
			page: usersPage.number,
			totalPages: usersPage.totalPages
		})),
	setLoading: (loading: boolean) => set(() => ({ loading })),
	setUsers: (users: User[]) => set(() => ({ users }))
}));

export const useUsers = () => {
	const store = useUsersStore();

	const fetchData = () => {
		store.setLoading(true);
		client
			.get(`/users?page=${store.page}`)
			.then(({ data }) => {
				store.setUsersPage(data);
			})
			.finally(() => {
				store.setLoading(false);
			});
	};

	useEffect(() => {
		fetchData();
	}, [store.page]); // eslint-disable-line

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
