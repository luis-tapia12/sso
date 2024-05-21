import { useState } from 'react';

import Modal from '../components/Modal';
import styles from '../styles/ConfirmationModal.module.css';

type ConfirmationModalProps = {
	show: boolean;
	onAccept: () => Promise<unknown>;
	onClose: () => void;
};

const ConfirmationModal = ({ onAccept, ...rest }: ConfirmationModalProps) => {
	const [submitting, setSubmitting] = useState(false);

	const handleAccept = () => {
		setSubmitting(true);
		onAccept()
			.then(() => {
				rest.onClose();
			})
			.finally(() => {
				setSubmitting(false);
			});
	};

	return (
		<Modal {...rest}>
			<div className={styles.confirmation}>
				<div>
					<i className="fas fa-triangle-exclamation" />
				</div>
				<div>Do you want to continue? This action cannot be undone.</div>
			</div>
			<div className={styles.actions}>
				<button disabled={submitting} onClick={handleAccept}>
					Accept
				</button>
				<button disabled={submitting} onClick={rest.onClose}>
					Cancel
				</button>
			</div>
		</Modal>
	);
};
export default ConfirmationModal;
