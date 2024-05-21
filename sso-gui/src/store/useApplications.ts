import { useEffect } from 'react';
import { create } from 'zustand';

import client from '../config/client';
import { Page } from '../types';
import { objectArray } from '../helpers/objectArray';

export type Application = {
	id: string;
	name: string;
	clientId: string;
	redirectUrl: string;
	logo: string;
};

type ApplicationState = {
	applications: Application[];
	page: number;
	totalPages: number;
	loading: boolean;
	sort: string;
	order: 'asc' | 'desc';
	setPage: (page: number) => void;
	setApplicationsPage: (applicationsPage: Page<Application>) => void;
	setLoading: (loading: boolean) => void;
	setApplications: (applications: Application[]) => void;
	setSort: (column: string) => void;
};

export const useApplicationsStore = create<ApplicationState>((set) => ({
	applications: [],
	page: 0,
	totalPages: 10,
	loading: true,
	sort: '',
	order: 'desc',
	setPage: (page: number) => set(() => ({ page })),
	setApplicationsPage: (applicationsPage: Page<Application>) =>
		set(() => ({
			applications: applicationsPage.content,
			page: applicationsPage.number,
			totalPages: applicationsPage.totalPages
		})),
	setLoading: (loading: boolean) => set(() => ({ loading })),
	setApplications: (applications: Application[]) => set(() => ({ applications })),
	setSort: (column) =>
		set((state) => ({ sort: column, order: state.order === 'asc' ? 'desc' : 'asc' }))
}));

export const useApplications = () => {
	const store = useApplicationsStore();

	const fetchData = () => {
		store.setLoading(true);
		client
			.get(`/clients?page=${store.page}&sort=${store.sort}&order=${store.order}`)
			.then(({ data }) => {
				store.setApplicationsPage(data);
			})
			.finally(() => {
				store.setLoading(false);
			});
	};

	useEffect(() => {
		fetchData();
	}, [store.page, store.sort, store.order]); // eslint-disable-line

	const handleCreate = async (application: Application) => {
		try {
			await client.post('/clients', { ...application, password: 'password' });
			fetchData();
		} catch (error) {
			console.log(error);
		}
	};

	const handleDelete = async (application: Application) => {
		return client.delete(`/clients/${application.id}`).then(() => {
			fetchData();
			return Promise.resolve();
		});
	};

	const handleUpdate = async (application: Application) => {
		try {
			const { data } = await client.patch(`/clients/${application.id}`, application);
			store.setApplications(objectArray(store.applications).update(data));
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
