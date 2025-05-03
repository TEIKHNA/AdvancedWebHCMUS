function FilterTask({ filterTitle = "", setFilterTitle }) {
  return (
    <div className="filter-task">
      <input
        type="text"
        value={filterTitle}
        onChange={(e) => setFilterTitle(e.target.value)}
        placeholder="Filter tasks..."
      />
    </div>
  );
}

export default FilterTask;