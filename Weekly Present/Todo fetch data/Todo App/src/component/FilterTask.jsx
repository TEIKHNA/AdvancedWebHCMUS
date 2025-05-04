function FilterTask({ filterTitle = "", setFilterTitle }) {
  return (
    <div >
      <input className="filter-input"
        type="text"
        value={filterTitle}
        onChange={(e) => setFilterTitle(e.target.value)}
        placeholder="Filter tasks..."
      />
    </div>
  );
}

export default FilterTask;