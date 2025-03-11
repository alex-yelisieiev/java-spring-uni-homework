import React from 'react';
import { Container, Typography, Paper, Box } from '@mui/material';
const HomePage = () => {
    return (
        <Container maxWidth="lg">
            <Box sx={{ mt: 4, mb: 4 }}>
                <Paper elevation={3} sx={{ p: 4 }}>
                    <Typography variant="h3" component="h1" gutterBottom>
                        Welcome to Our Application
                    </Typography>
                    <Typography variant="h6" color="textSecondary" paragraph>
                        This is your one-stop solution for managing your tasks and activities.
                        Navigate through the menu to explore different features.
                    </Typography>
                </Paper>
            </Box>
        </Container>
    );
};

export default HomePage;
