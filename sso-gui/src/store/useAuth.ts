import { create } from 'zustand';
import { User } from './useUsers';

type AuthState = {
	user: User;
	loading: boolean;
	setUser: (user: User) => void;
	setLoading: (loading: boolean) => void;
};

export const useAuth = create<AuthState>((set) => ({
	user: null!,
	loading: true,
	setUser: (user) => set(() => ({ user })),
	setLoading: (loading) => set(() => ({ loading }))
}));
