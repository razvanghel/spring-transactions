import React, { useState } from 'react';
import axios from 'axios';

const CreateTransactionForm = ({ onTransactionCreated }) => {
    const [recipientAccountId, setRecipientAccountId] = useState('');
    const [amount, setAmount] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('/transactions', {
                accountId: recipientAccountId,
                amount: parseFloat(amount)
            });
            if (response.status === 201) {
                onTransactionCreated();
            } else {
                alert('Failed to create transaction');
            }
        } catch (error) {
            console.error('Error creating transaction:', error);
            alert('Failed to create transaction');
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Recipient Account ID:</label>
                <input
                    type="text"
                    value={recipientAccountId}
                    onChange={(e) => setRecipientAccountId(e.target.value)}
                    required
                />
            </div>
            <div>
                <label>Amount:</label>
                <input
                    type="number"
                    value={amount}
                    onChange={(e) => setAmount(e.target.value)}
                    required
                />
            </div>
            <button type="submit">Create Transaction</button>
        </form>
    );
};

export default CreateTransactionForm;
