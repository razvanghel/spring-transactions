import React, { useState, useEffect } from 'react';
import axios from 'axios';

const TransactionList = ({ accountId }) => {

    const [transactions, setTransactions] = useState([]);
     const [selectedTransactions, setSelectedTransactions] = useState(null);

    const fetchAccountTransactions = async () => {
        try {
            const response = await axios.get(`/transactions/account/${accountId}`);
            setTransactions(response.data);
        } catch (error) {
            console.error('Error fetching account transactions:', error);
        }
    };

    useEffect(() => {
        fetchAccountTransactions();
    }, [accountId]);

    return (
        <div>
            <h3>Transactions for Account ID: {accountId}</h3>
            {transactions.length > 0 ? (
                <table>
                    <thead>
                        <tr>
                            <th>Transaction ID</th>
                            <th>Amount</th>
                            <th>Timestamp</th>
                        </tr>
                    </thead>
                    <tbody>
                        {transactions.map(transaction => (
                            <tr key={transaction.id}>
                                <td>{transaction.id}</td>
                                <td>{transaction.amount}</td>
                                <td>{transaction.timestamp}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            ) : (
                <p>No transactions found for this account.</p>
            )}
        </div>
    );
};

export default TransactionList;
