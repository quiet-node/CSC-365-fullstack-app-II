import { useEffect } from 'react';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

interface Props {
  similarBs: any[];
}

export default function BusinessTable({ similarBs }: Props): JSX.Element {
  if (similarBs != null) {
    console.log(similarBs.length);
  }

  return (
    <Paper>
      <Table className='w-full'>
        <TableHead>
          <TableRow>
            <TableCell align='center' width='50px'>
              Similarity Rate
            </TableCell>
            <TableCell align='center' width='200px'>
              Business Name
            </TableCell>
            <TableCell align='center' width='350px'>
              Categories
            </TableCell>
            <TableCell align='center' width='50px'>
              Reviews
            </TableCell>
            <TableCell align='center' width='50px'>
              Stars
            </TableCell>
            <TableCell align='center' width='200px'>
              Address
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {/* {rows.map((row) => ( */}
          {/* <TableRow key={row.id}> */}
          <TableRow>
            <TableCell align='center'>59.82 %</TableCell>
            <TableCell align='center'>Great Clips</TableCell>
            <TableCell align='center'>
              Hair Salons, Spa, Mssage, Hair Salons, Spa, Mssage, Hair Salons,
              Spa, Mssage, Hair Salons, Spa, Mssage, Hair Salons, Spa, Mssage,
            </TableCell>
            <TableCell align='center'>150</TableCell>
            <TableCell align='center'>4.4</TableCell>
            <TableCell align='center'>15 Union Rod</TableCell>
          </TableRow>
        </TableBody>
      </Table>
    </Paper>
  );
}
