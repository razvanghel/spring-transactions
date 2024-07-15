import React, { useState } from 'react';
import axios from 'axios';
const CreateUserForm = ({ onUserCreated }) => {
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');

    const handleSubmit = async (e) => {
            e.preventDefault();
            try {
                const response = await axios.post('/users', {
                    name,
                    surname
                });
                if (response.status === 201) {
                    onUserCreated();
                    setName('');
                    setSurname('');
                } else {
                    alert('Failed to create user');
                }
            } catch (error) {
                console.error('There was an error creating the user!', error);
                alert('Failed to create user');
            }
        };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Name:</label>
                <input
                    type="text"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    required
                />
            </div>
            <div>
                <label>Surname:</label>
                <input
                    type="text"
                    value={surname}
                    onChange={(e) => setSurname(e.target.value)}
                    required
                />
            </div>
            <button type="submit">Create User</button>
        </form>
    );
};

export default CreateUserForm;
