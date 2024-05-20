import Modal from '../components/Modal';

type ConfirmationModalProps = {
	show: boolean;
	onAccept: () => void;
	onClose: () => void;
};

const ConfirmationModal = ({ onAccept, ...rest }: ConfirmationModalProps) => {
	return (
		<Modal {...rest} title="Confirm">
			<div>Do you want to continue? This action cannot be undone.</div>
			<div>
				<button onClick={onAccept}>Accept</button>
				<button onClick={rest.onClose}>Cancel</button>
			</div>
		</Modal>
	);
};
export default ConfirmationModal;
