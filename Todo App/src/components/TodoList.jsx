import { List, ListItem, ListItemText } from "@mui/material";

export default function TodoList({ tasks }) {
  return (
    <List>
      {tasks.map((task) => (
        <ListItem key={task.id}>
          <ListItemText primary={task.name} />
        </ListItem>
      ))}
    </List>
  );
}
