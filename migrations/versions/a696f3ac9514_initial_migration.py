"""Initial migration.

Revision ID: a696f3ac9514
Revises: 
Create Date: 2023-06-16 13:15:07.453782

"""
from alembic import op
import sqlalchemy as sa


# revision identifiers, used by Alembic.
revision = 'a696f3ac9514'
down_revision = None
branch_labels = None
depends_on = None


def upgrade():
    # ### commands auto generated by Alembic - please adjust! ###
    op.drop_table('status_logs')
    # ### end Alembic commands ###


def downgrade():
    # ### commands auto generated by Alembic - please adjust! ###
    op.create_table('status_logs',
    sa.Column('id', sa.INTEGER(), nullable=False),
    sa.Column('status', sa.VARCHAR(), nullable=True),
    sa.Column('message', sa.VARCHAR(), nullable=True),
    sa.Column('timestamp', sa.DATETIME(), nullable=True),
    sa.PrimaryKeyConstraint('id')
    )
    # ### end Alembic commands ###