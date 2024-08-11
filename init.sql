CREATE TABLE IF NOT EXISTS outbox (
                        id SERIAL PRIMARY KEY,
                        aggregate_id UUID NOT NULL,
                        aggregate_type VARCHAR(255) NOT NULL,
                        payload JSONB NOT NULL,
                        event_type VARCHAR(255) NOT NULL,
                        timestamp TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);