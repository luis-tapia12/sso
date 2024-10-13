export const isNotBlank = (value: string) => {
	if (!value?.trim?.()) {
		return 'This field is required';
	}

	return true;
};

export const isUsername = (value: string) => {
	const startsWithLetter = /^[a-zA-Z]/;
	const justOneDot = /(?!.*\.\.)/;
	const justOneUnderscore = /(?!.*__)/;
	const endsWithLetter = /[a-zA-Z0-9]$/;
	const regexp = /^[a-zA-Z][a-zA-Z0-9._]*[a-zA-Z0-9]$/;

	if (!startsWithLetter.test(value)) {
		return 'Must start with a letter';
	}

	if (!justOneDot.test(value)) {
		return 'Must have only one consecutive dot';
	}

	if (!justOneUnderscore.test(value)) {
		return 'Must have only one consecutive dot';
	}

	if (!endsWithLetter.test(value)) {
		return 'Must end with a letter';
	}

	if (!regexp.test(value)) {
		return 'Invalid character';
	}

	return true;
};

export const isName = (value: string) => {
	const notStartsWithSpace = /^(?! )/;
	const startsWithLetter = /[A-Za-záéíóúÁÉÍÓÚñÑ]+/;
	const hasLetterAfterSpace = /( [A-Za-záéíóúÁÉÍÓÚñÑ]+)*/;
	const notEndsWithSpace = /(?<! )$/;
	const justOneSpace = / {2}/;

	if (!notStartsWithSpace.test(value)) {
		return 'Must not start with a space';
	}

	if (!startsWithLetter.test(value)) {
		return 'Must start with a letter';
	}

	if (justOneSpace.test(value)) {
		return 'Must have only one consecutive space';
	}

	if (!hasLetterAfterSpace.test(value)) {
		return 'Must have a letter after a space';
	}

	if (!notEndsWithSpace.test(value)) {
		return 'Must not end with a space';
	}

	return true;
};
